import { myAxios } from "./helper";


export const signup = (user) => {
  return myAxios.post("/user/create-user", user)
    .then((response) => response.data)
    .catch((error) => {
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        console.error("Server responded with an error status:", error.response.status);
        console.error("Response data:", error.response.data);
      } else if (error.request) {
        // The request was made but no response was received
        console.error("No response received from the server");
      } else {
        // Something happened in setting up the request that triggered an Error
        console.error("Error setting up the request:", error.message);
      }
      // Throw the error again to propagate it to the calling function
      throw error;
    });
  }


  export const loginUser = (loginDetail) => {
    return myAxios.post("/auth/login", loginDetail)
      .then((response) => response.data)
      .catch((error) => {
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          console.error("Server responded with an error status:", error.response.status);
          console.error("Response data:", error.response.data);
        } else if (error.request) {
          // The request was made but no response was received
          console.error("No response received from the server");
        } else {
          // Something happened in setting up the request that triggered an Error
          console.error("Error setting up the request:", error.message);
        }
        // Throw the error again to propagate it to the calling function
        throw error;
      });
    }

export const isUsernameAvailable = (username) => {
  return myAxios
    .get(`/user/isUsernameAvailable/${username}`)
    .then((response) => response.data);
}

export const isEmailAvailable = (email) => {
  return myAxios
    .get(`/user/isEmailAvailable/${email}`)
    .then((response) => response.data);
}


export const findUserByEmail = (email) => {
  return myAxios
    .get(`/user/findUserByEmail/${email}`)
    .then((response) => response.data);
}