document.querySelector("#playerreg").addEventListener("submit",function(e) {
    e.preventDefault();
    let x = document.querySelector("#playerreg").elements;

    let first_name = x["firstnameregister"].value;
    let last_name = x["lastnameregister"].value;
    let username = x["usernameregister"].value;
    let email = x["emailreg"].value;
    let password= x["Passwordregister"].value;
    let password2= x["confirmPasswordregister"].value;

    if (password != password2) {
        window.alert("Registration failed: passwords do not match")
    } else {
        const data = {
            "first_name": first_name,
            "last_name": last_name,
            "username": username,
            "password": password,
            "email": email
        }
        playerReg(data)
    }
  });

function playerReg(data) {
    let csrfToken = $("meta[name='_csrf']").attr("content")
    fetch("http://localhost:8010/player/reg", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrfToken
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (response.status===200) {
            window.alert(response)
            return;
        }
        response.text().then((data) => {
            window.alert(data);
        })
    })
    
    .catch(function(error) {
        console.log("Request failed", error);
    })
}