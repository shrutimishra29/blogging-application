import { myAxios } from "./helper";
import { privateAxios } from "./helper";


//create post function
export const createNewPost = async (formData, categoryId, userId) => {
    try {
      // Append additional form data parameters
      formData.append('categoryId', categoryId);
      formData.append('userId', userId);
  
      // Make the API call
      const createdPost = await privateAxios.post(`/posts/add/category/${categoryId}/user/${userId}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
  
      return createdPost;
    } catch (error) {
      // Handle errors
      throw error;
    }
  };
  

//Load all posts
export const getAllPosts = (pageNumber, pageSize) => {
    return privateAxios.get(`/posts/all?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=addedDate`).then(response => response.data);
}

export const getPostById = (postId) => {
    return privateAxios.get(`/posts/${postId}`).then(response => response.data);
}

export const getImageFromPost = (postId) => {
    return privateAxios.get(`/posts/image/${postId}`).then(response => response.data);
}

export const getImage = (imageName) => {
    return privateAxios({
        url: `/posts/image/${imageName}`,
        method: 'GET',
        responseType: 'blob',
    })
    .then(response => response.data)
}

//upload post banner image

export const uploadPostBannerImage = (image, postId) => {
    let formdata = new FormData();
    formdata.append('image', image);
    return privateAxios.post(`/posts/image/upload/${postId}`, formdata,{
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).then(response => response.data);
} 

export const deletePost = (postId) => {
    return privateAxios.delete(`/posts/${postId}`).then(response => response.data);
}