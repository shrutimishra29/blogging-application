import { privateAxios } from "./helper";

export const getAllComments = (postId) => {
    return privateAxios(`/comments/post/${postId}`).then(response => response.data)
}
export const createComment = (content, postId, userId) => {
    const commentData = {
      content,
      addedDate: new Date().toISOString(), // Set addedDate to the current timestamp
    };
  
    return privateAxios.post(`/comments/addComment/${postId}/user/${userId}`, commentData)
      .then(response => response.data)
      .catch(error => {
        console.error(error);
        throw error;
      });
  };
  

export const deleteComment = (commentId) => {
    return privateAxios.delete(`/comments/deleteComment/${commentId}`).then(response => response.data)
}