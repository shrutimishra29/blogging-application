//is LoggedIn =>

export const isLoggedIn = () => {
    let data = localStorage.getItem("data");
    if(data != null) return true;
    return false;
}
//doLogin => set data to local storage

export const doLogin = (data) => {
    localStorage.setItem("data",JSON.stringify(data))

    //Redirects to userDashboard
}

//doLogout => remove data from local storage

export const doLogout = (next) => {
    localStorage.removeItem("data");
    next()
}

//get currentUser

 export const getCurrentUser = () => {
   if(isLoggedIn){
    return JSON.parse(localStorage.getItem("data"))?.user;
   }
   else{
    return undefined;
   }
 } 

 export const getToken= () =>{
    if(isLoggedIn){
        return JSON.parse(localStorage.getItem("data"))?.jwtToken;
    }
    return null;
 }