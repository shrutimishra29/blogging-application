import React, { useEffect, useState } from 'react';
import { Col, Container, Row } from 'reactstrap';
import Post from './Post';
import InfiniteScroll from 'react-infinite-scroll-component';
import { getAllPosts } from '../services/post-service';
import { toast } from 'react-toastify';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import './styles/newfeed.css';

const Loader = () => (
  <div className="loader-container">
    <FontAwesomeIcon icon={faSpinner} spin />
  </div>
);

function NewFeed() {
  const [postContent, setPostContent] = useState({
    content: [],
    totalPages: '',
    totalElements: '',
    pageSize: '',
    lastPage: false,
    pageNumber: '',
  });

  useEffect(() => {
    // Load all posts from the server
    getAllPosts(0, 5)
      .then((data) => {
        setPostContent({
          content: [...postContent.content, ...data.content],
          totalPages: data.totalPages,
          totalElements: data.totalElements,
          pageSize: data.pageSize,
          lastPage: data.lastPage,
          pageNumber: data.pageNumber,
        });
      })
      .catch((err) => {
        console.log(err);
      });
  }, []); // Empty dependency array to run the effect only once

  const [currentPage, setCurrentPage] = useState(0);

  useEffect(() => {
    changePage(currentPage);
  }, [currentPage]);

  const changePage = (pageNumber = 0, pageSize = 5) => {
    if (pageNumber > postContent.pageNumber && postContent.lastPage) {
      return;
    }

    getAllPosts(pageNumber, pageSize)
      .then((data) => {
        setPostContent(data);
        window.scroll(0, 0);
      })
      .catch((error) => {
        toast.error('Error in loading posts!!');
        console.log(error);
      });
  };

  const changePageInfinite = () => {
    if (postContent.lastPage) {
      return;
    }

    const nextPage = postContent.pageNumber + 1;
    setTimeout(() => {
      getAllPosts(nextPage, 5)
      .then((data) => {
        setPostContent({
          content: [...postContent.content, ...data.content],
          totalPages: data.totalPages,
          totalElements: data.totalElements,
          pageSize: data.pageSize,
          lastPage: data.lastPage,
          pageNumber: data.pageNumber,
        });
      })
      .catch((error) => {
        toast.error('Error in loading posts!!');
        console.log(error);
      });
    }, 2000)
   
  };

  return (
    <div className="border-0 shadow-sm mt-5">
      <h3>Look what others have posted!</h3>
      <Row>
        <Col>
          {postContent.content.map((post) => (
            <Post post={post} key={post.postId} />
          ))}

          <InfiniteScroll
            dataLength={postContent.content.length}
            next={changePageInfinite}
            hasMore={!postContent.lastPage}
            loader={<Loader />}
          ></InfiniteScroll>
        </Col>
      </Row>
    </div>
  );
}

export default NewFeed;
