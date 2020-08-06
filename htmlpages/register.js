function playerReg(data) {

    fetch("http://localhost:8010/player/register", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    }).then(response => response)
    .then(function (data) {
        console.log("Request succeeded with JSON response",data);
    }).catch(function(error) {
        console.log("Request failed", error);
    })

}

document.querySelector("#playerreg").addEventListener("submit",function(e) {
    e.preventDefault();
    let x = document.querySelector("#playerreg").elements;

    let first_name = x["firstnameregister"].value;
    let last_name = x["lastnameregister"].value;
    let username = x["usernameregister"].value;
    let password1= x["Passwordregister"].value;
    let password2= x["confirmPasswordregister"].value;

    if (password1 != password2) {
        window.alert("Registration failed: passwords must match")
    } else if (first_name==="" || last_name==="" || username==="" || password1==="") {
        window.alert("Registration failed: All fields must contain characters")
    } else {
        const data = {
            "first_name": first_name,
            "last_name": last_name,
            "username": username,
            "password1": password1
        }
        playerReg(data)
        window.location.href="Account.html"
    }
  });
