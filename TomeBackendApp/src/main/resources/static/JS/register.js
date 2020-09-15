// let Pid = sessionStorage.getItem("Pid")
// if (Pid) window.location.href="Account.html";

document.querySelector("#playerreg").addEventListener("submit",function(e) {
    e.preventDefault();
    let x = document.querySelector("#playerreg").elements;

    let first_name = x["firstnameregister"].value;
    let last_name = x["lastnameregister"].value;
    let username = x["usernameregister"].value;
    let password= x["Passwordregister"].value;
    let password2= x["confirmPasswordregister"].value;

    if (first_name==="" || last_name==="" || username==="" || password==="") {
        window.alert("Registration failed: All fields must contain characters")} 
    else if (password != password2) {
        window.alert("Registration failed: passwords must match")
    } else {
        const data = {
            "first_name": first_name,
            "last_name": last_name,
            "username": username,
            "password": password
        }
        playerReg(data)
        // window.location.href="Account.html"
    }
  });

function playerReg(data) {
    console.log(data);
    let username=data.username
    let password=data.password

    fetch("http://localhost:8010/player/register", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.status===500) {
            window.alert("Registration unsuccessful, it is probable that the username you entered is already in use")
            return;
        }
        response.json().then((data) => {
            console.log("Request succeeded with JSON response",data);
            login(username,password)
        })
    })
    
    .then().catch(function(error) {
        console.log("Request failed", error);
    })

}



  document.querySelector("#loginform").addEventListener("submit", (l) => {
      l.preventDefault()
      let logs = document.querySelector("#loginform").elements
      let usern = logs["usernamelogin"].value
      let pw = logs["passwordlogin"].value
      login(usern, pw)

  })
function login(user,pw) {
    
    fetch(`http://localhost:8010/player/find/${user}`)
    .then(
        function(response) {
            if (response.status!==200) {
                console.log("There was a problem, status code " + response.status);
                window.alert("Login unsuccessful: It may be that the username you entered was incorrect")
                return;
                
            }
            response.json().then(function(data) {
                if (pw==data.password) {
                    console.log("login successful");
                    sessionStorage.setItem("Pid",`${data.player_id}`)
                    window.location.href="accounthome"
                }
                else {
                    window.alert("Login unsuccessful: Password entered was incorrect")
                    location.reload()
                }
            })
  }).catch(function(error) {
    console.log("Request failed", error);
  })
}
