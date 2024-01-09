import React from 'react';
import { Button, Card, CardBody, CardText } from 'reactstrap';
import parse from 'html-react-parser'
import { Link } from 'react-router-dom';
import './styles/post.css'
function Post({ post }) {
  return (
   
    <Card className='border-0 shadow-sm mb-3' body>
      <CardBody>
        <h3>{post.title}</h3>
        {/* <CardText dangerouslySetInnerHTML={{__html: post.content}}> 
         
        </CardText> */}
        <div>
         <Link className="btn btn-secondary " to={"/post/" + post.postId}>Read more</Link>
        </div>
      </CardBody>
    </Card>
  );
}


export default Post;
