import React, { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import {
  Button,
  Card,
  CardBody,
  CardText,
  Col,
  Container,
  Input,
  Row,
} from "reactstrap";
import { deletePost, getImage, getPostById } from "../services/post-service";
import { toast } from "react-toastify";
import {
  createComment,
  deleteComment,
  getAllComments,
  createNewPost,
} from "../services/comment-service";
import { getCurrentUser } from "../components/auth";
import { faTrash, faTrashAlt, faUser } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import "../components/styles/postpage.css";

const PostPage = () => {
  const { postId } = useParams();
  const navigate = useNavigate();
  const [post, setPost] = useState(null);
  const [imageName, setimageName] = useState(null);
  const [comments, setComments] = useState([]);
  const [user, setUser] = useState();
  const [newComment, setNewComment] = useState("");

  useEffect(() => {
    // Set login user details
    setUser(getCurrentUser());

    // Load post from post Id
    getPostById(postId)
      .then((data) => {
        console.log(data);
        setPost(data);
        if (data.imageName) {
          getImage(data.imageName)
            .then((imageData) => {
              const url = URL.createObjectURL(imageData);
              setimageName(url);
            })
            .catch((error) => {
              console.log(error);
              toast.error("Error loading image");
            });
        }
      })
      .catch((error) => {
        console.log(error);
        toast.error("Error loading post");
      });

    // Load comments from backend
    getAllComments(postId)
      .then((data) => {
        console.log("Comments :", data);
        setComments(data);
      })
      .catch((error) => {
        console.log(error);
        toast.error("Error loading comments");
      });
  }, [postId]);

  const formatDate = (dateString) => {
    const commentDate = new Date(dateString);
    const today = new Date();

    if (isSameDay(commentDate, today)) {
      // Display time if the comment was made today
      const options = { hour: "numeric", minute: "numeric", hour12: true };
      const formattedTime = new Intl.DateTimeFormat("en-US", options).format(
        commentDate
      );
      return formattedTime;
    } else {
      // Display complete date for comments made on a different day
      const options = {
        year: "numeric",
        month: "long",
        day: "numeric",
        hour: "numeric",
        minute: "numeric",
        hour12: true,
      };
      const formattedDate = new Intl.DateTimeFormat("en-US", options).format(
        commentDate
      );
      return formattedDate;
    }
  };

  const isSameDay = (date1, date2) => {
    return (
      date1.getDate() === date2.getDate() &&
      date1.getMonth() === date2.getMonth() &&
      date1.getFullYear() === date2.getFullYear()
    );
  };

  const submitComment = () => {
    createComment(newComment, post.postId, user.userId)
      .then((data) => {
        console.log(data);
        toast.success("Comment added successfully");
        setComments([...comments, data]);
        setNewComment("");
      })
      .catch((error) => {
        console.log(error);
        toast.error("Error adding comment");
      });
  };

  const deleteComments = (commentId) => {
    deleteComment(commentId)
      .then((response) => {
        console.log(response);
        toast.success("Comment deleted successfully");
        setComments(
          comments.filter((comment) => comment.commentId !== commentId)
        );
      })
      .catch((error) => {
        console.log(error);
        toast.error("Error deleting comment");
      });
  };

  const deletePosts = (postId) => {
    deletePost(postId)
      .then((response) => {
        toast.success("Post deleted successfully");
        setTimeout(() => {
          window.location.href = "/user/dashboard";
        }, 3000);
      })
      .catch((error) => {
        toast.error("Error is deleting post.");
      });
  };
  return (
    <Container className="mt-4">
      <Link to="/">Home</Link>/ {post && <Link to="">{post.title}</Link>}
      <Row>
        <Col md={{ size: 12 }}>
          <Card className="mt-3">
            {post && (
              <CardBody>
                <CardText>
                  Posted by <b>{post.user.username} </b>
                  on {formatDate(post.addedDate)}
                </CardText>
                <CardText>
                  <span className="text-muted">
                    <i>{post.category?.categoryName}</i>
                  </span>
                </CardText>
                <div
                  className="divider"
                  style={{
                    width: "100%",
                    height: "1px",
                    background: "#e2e2e2",
                  }}
                ></div>
                <CardText className="mt-3">
                  <div
                    style={{
                      display: "flex",
                      justifyContent: "space-between",
                      alignItems: "center",
                    }}
                  >
                    <h3>{post.title}</h3>
                    {user && post.user.userId === user.userId && (
                      <Button
                        type="submit"
                        onClick={() => deletePosts(post.postId)}
                        className="hover-red"
                      >
                        <FontAwesomeIcon icon={faTrash} />
                      </Button>
                    )}
                  </div>
                </CardText>

                <div className="image" style={{ textAlign: "center" }}>
                  {imageName && (
                    <img
                      className="image-container mt-3 shadow rounded"
                      src={imageName}
                      style={{ maxWidth: "50%" }}
                      alt="Post"
                    />
                  )}
                </div>
                <CardText
                  className="mt-5"
                  dangerouslySetInnerHTML={{ __html: post.content }}
                ></CardText>
              </CardBody>
            )}
          </Card>
        </Col>
        <Row className="mt-4">
          <Col
            md={{
              size: 12,
              offset: 0,
            }}
          >
            <h3>Comments</h3>

            <Card className="mt-4">
              <CardBody>
                <Input
                  type="textarea"
                  placeholder="Enter your comment here"
                  value={newComment}
                  onChange={(event) => setNewComment(event.target.value)}
                />
                <div className="mt-4">
                  <Button type="submit" onClick={submitComment} color="primary">
                    Submit
                  </Button>
                </div>
              </CardBody>
            </Card>
            {comments &&
              comments.map((commentItem) => (
                <Card key={commentItem.commentId} className="mt-3">
                  <CardBody>
                    <CardText>
                      <div
                        className="flex-container"
                        style={{
                          display: "flex",
                          justifyContent: "space-between",
                        }}
                      >
                        <div>
                          <FontAwesomeIcon icon={faUser} />
                          <b> {commentItem.user.username}</b>
                        </div>
                        <div>{formatDate(commentItem.addedDate)}</div>
                      </div>
                    </CardText>
                    <div
                      style={{
                        display: "flex",
                        justifyContent: "space-between",
                        alignItems: "center", // Align items vertically in the center
                      }}
                    >
                      <div>{commentItem.content}</div>
                      {user && commentItem.user.userId === user.userId && (
                        <Button
                          type="submit"
                          onClick={() => deleteComments(commentItem.commentId)}
                          className="hover-red"
                        >
                          <FontAwesomeIcon icon={faTrashAlt} />
                        </Button>
                      )}
                    </div>
                  </CardBody>
                </Card>
              ))}
          </Col>
        </Row>
      </Row>
    </Container>
  );
};

export default PostPage;
