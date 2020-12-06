let Pid=parseInt(sessionStorage.getItem("Pid"))
if(!(Pid)) window.location.href="login.html";

document.querySelector("#charcreate").addEventListener("submit", function(a) {
    a.preventDefault()

    fetch(`http://localhost:8010/player/view/${Pid}`)
    .then(
    function(response) {
        if (response.status!==200) {
            console.log("There was a problem, status code " + response.status);
            return;
        }
        response.json().then(function(data) {
            console.log(data);
            let v = document.querySelector("#charcreate").elements

            let name = v["charnameinput"].value
            let race = v["chooserace"].value
            let origin = v["regionselect"].value
            let bg = v["backgroundentry"].value

            const newChar = {
                "char_name": name,
                "race": race,
                "origin": origin,
                "char_background": bg,
                "player": data,
                "alive": 1,
                "hp": 3,
                "mp": 3,
                "ap_basic": 1         
    }
    if (name==="") {
        window.alert("Character name cannot be blank")
    } else {
        saveChar(newChar)
    }
        })}).catch(function(error) {
            console.log("Request failed", error);
        })

    
    
})

function saveChar(data) {
    let csrfToken = $("meta[name='_csrf']").attr("content")
    fetch("http://localhost:8010/character/create", {
        method: "POST",
        mode: "cors",
        headers: {
            "Content-Type": "application/json",
            "X-CSRF-TOKEN": csrfToken
        },
        body: JSON.stringify(data)
    }).then(response => response)
    .then(function(data) {
        console.log("Request succeeded with JSON response",data);
        window.location.href="accounthome"
    }).then(function(error) {
        console.log("Request failed", error);
    })
}

document.querySelector("#backtoaccount").addEventListener("click", (a) => {
    a.preventDefault
    window.location.href="accounthome"
})