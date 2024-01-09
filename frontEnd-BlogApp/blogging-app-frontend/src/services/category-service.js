import { myAxios } from "./helper";

export const loadAllCategories = () =>{
    return myAxios.get("/category/all").then(response => {return response.data})
}