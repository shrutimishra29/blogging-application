import { Card, Container, CardBody, CardLink, Button } from "reactstrap";
import AddPost from "../../components/AddPost";
import { Component, useEffect, useState } from "react";
import NewFeed from "../../components/newFeed";
import { getAllPosts } from "../../services/post-service";
import Post from "../../components/Post";
import { getCurrentUser } from "../../components/auth";
import '../user-pages/userdashboard.css'
const UserDashboard = () => {
  const [userFullName, setUserFullName] = useState();
  const [greet, setGreet] = useState();
  const [newPostOpen, setNewPostOpen] = useState({
    text: "Create new post",
    isOpen: false,
  });

  useEffect(() => {
    const user = getCurrentUser();
    const fullName = user.firstName + " " + user.lastName;
    setUserFullName(fullName);
    console.log(fullName);

    const time = new Date().getHours();
    const currentTime = new Date().getHours();
    let greeting = "";
    if (currentTime >= 5 && currentTime < 12) {
      greeting = "Good Morning";
    } else if (currentTime >= 12 && currentTime < 17) {
      greeting = "Good Afternoon";
    } else {
      greeting = "Good Evening";
    }
    console.log(greeting);
    setGreet(greeting);
  }, []);

  const openNewPost = () => {
    setNewPostOpen({
      isOpen: !newPostOpen.isOpen,
      text: newPostOpen.isOpen ? "Create new post" : "Close",
    });
    console.log(newPostOpen.isOpen);
  };

  return (
    <div className="container mt-5">
      {/* <Post post={{ title: "wfwf", content: "fwfwffw" }} /> */}
      <h3>
        Hi {userFullName}, {greet}
      </h3>
      <div className="NewPost">
        <Card>
          <CardBody>
          <p>
          Your stories matter! Share your unique perspective and let your
            voice be heard. We can't wait to hear what you've been up to or
            what's on your mind. Post away and let the community celebrate the
            diversity of our experiences! 🚀 #ShareYourStory #CommunityVoi
            #ConnectWithUs"</p> 
            <Button className="align-items-center"onClick={openNewPost}>{newPostOpen.text}</Button>
          </CardBody>
          {newPostOpen.isOpen && <AddPost />}
        </Card>
      </div>
      <div className="wrapper">
        <NewFeed />
      </div>
    </div>
  );
};



export default UserDashboard;
