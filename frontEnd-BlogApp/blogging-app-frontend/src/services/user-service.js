import { myAxios } from "./helper";


export const signup = (user) => {
  return myAxios
    .post("/api/v1/user/create-user", user)
    .then((response) => response.data);
};

