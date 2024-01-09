import { useEffect, useRef, useState } from "react";
import { Button, Card, CardBody, Container, Form, Input, Label } from "reactstrap";
import { loadAllCategories } from "../services/category-service";
import JoditEditor from "jodit-react";
import { createNewPost, uploadPostBannerImage } from "../services/post-service";
import { getCurrentUser } from "./auth";
import { toast } from "react-toastify";

const AddPost = () => {
  const editor = useRef(null);
  const [content, setContent] = useState('');
  const [categories, setCategories] = useState([]);
  const [user, setUser] = useState(undefined);
  const [image, setImage] = useState(null);
  const [post, setPost] = useState({
    title: "",
    content: "",
    categoryId: ""
  });

  useEffect(() => {
    setUser(getCurrentUser());
    loadAllCategories()
      .then((data) => {
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const fieldChanged = (event) => {
    setPost({ ...post, [event.target.name]: event.target.value });
  };

  const contentFieldChange = (data) => {
    setPost({ ...post, content: data });
  };

  const createPost = (event) => {
    event.preventDefault();
  
    if (post.title.trim() === '') {
      alert("Title is required");
      return;
    }
  
    if (post.content.trim() === '') {
      alert("Content is required");
      return;
    }
  
    if (post.categoryId.trim() === '') {
      alert("Category is required");
      return;
    }
  
    // Create postRequestDto JSON string
    const postRequestDto = JSON.stringify({
      // Populate postRequestDto with relevant data
      // You may need to adjust this based on your data structure
      // For example: title, content, etc.
      title: post.title,
      content: post.content,
    });
  
    // Submit form to server
    post['userId'] = user.userId;
  
    const formData = new FormData();
    formData.append('postRequestDto', postRequestDto);
    // formData.append('categoryId', post.categoryId);
    // formData.append('userId', post.userId);
    formData.append('file', image);
  
    createNewPost(formData, post.categoryId, user.userId)
      .then((data) => {
        // Handle successful post creation
        console.log(data);
        toast.success("Post created successfully");
  
        // Reset form fields
        setPost({
          title: "",
          content: "",
          categoryId: ""
        });
        setContent('');
  
        // Additional logic if needed
      })
      .catch((error) => {
        console.log(error);
        toast.error("Error creating post");
      });
  };
  
  const handleFileChange = (event) => {
    setImage(event.target.files[0]);
  };

  return (
    <div className="wrapper" style={{ marginLeft: "10px" }}>
      <Card className="shadow">
        <CardBody>
          <h3>What's going in your mind?</h3>
          <Form onSubmit={createPost}>
            <div className="my-3">
              <Label for="title">Post Title</Label>
              <Input
                type="text"
                name="title"
                id="title"
                placeholder="Enter here"
                className="rounded-0"
                onChange={fieldChanged}
                value={post.title}
                required
              />
            </div>
            <div className="my-3">
              <Label for="content">Post Content</Label>
              <JoditEditor
                ref={editor}
                value={content}
                onChange={(data) => {
                  setContent(data);
                  contentFieldChange(data);
                }}
              />
            </div>
            <div className="mt-3">
              <Label for="image">Select post banner</Label>
              <Input id="image" type="file" onChange={(event) => handleFileChange(event)} />
            </div>
            <div className="my-3">
              <Label for="categoryId">Post Category</Label>
              <Input
                type="select"
                name="categoryId"
                id="categoryId"
                placeholder="Enter here"
                className="rounded-0"
                onChange={fieldChanged}
                value={post.categoryId}
                required
              >
                <option disabled value="">
                  --Select Category--
                </option>
                {categories.map((category) => (
                  <option value={category.categoryId} key={category.categoryName}>
                    {category.categoryName}
                  </option>
                ))}
              </Input>
            </div>
            <Container className="text-center">
              <Button type="submit" rounded-0 color="primary">
                Create Post
              </Button>
              <Button
                className="rounded-0 ms-2"
                color="danger"
                onClick={() => {
                  setPost({
                    title: "",
                    content: "",
                    categoryId: ""
                  });
                  setContent('');
                }}
              >
                Reset Content
              </Button>
            </Container>
          </Form>
        </CardBody>
      </Card>
    </div>
  );
};

export default AddPost;
