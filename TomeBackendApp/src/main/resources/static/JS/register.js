// let Pid = sessionStorage.getItem("Pid")
// if (Pid) window.location.href="Account.html";

document.querySelector("#playerreg").addEventListener("submit",function(e) {
    e.preventDefault();
    let x = document.querySelector("#playerreg").elements;

    let first_name = x["firstnameregister"].value;
    let last_name = x["lastnameregister"].value;
    let username = x["usernameregister"].value;
    let email = x["emailreg"].value;
    let password= x["Passwordregister"].value;
    let password2= x["confirmPasswordregister"].value;

    if (first_name==="" || last_name==="" || username==="" || password==="") {
        window.alert("Registration failed: All fields must contain characters")} 
    else if (password != password2) {
        window.alert("Registration failed: passwords must match")
    } else {
        const data = {
            "firstName": first_name,
            "lastName": last_name,
            "username": username,
            "password": password,
            "mathchingPassword":password2,
            "email": email
        }
        playerReg(data)
        // window.location.href="Account.html"
    }
  });

function playerReg(data) {
        
    fetch("http://localhost:8010/player/register", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.status===200) {
            window.alert(response)
            return;
        }
        response.json().then((data) => {
            console.log("Request succeeded with JSON response",data);
        })
    })
    
    .then().catch(function(error) {
        console.log("Request failed", error);
    })

}
