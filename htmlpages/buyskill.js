document.querySelector("#armourdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Armour Tree"
    document.getElementById("picspace").innerHTML="<img src='armourtree.png' width=100%>"
    fetch(`http://localhost:8010/skills/listbytree/1`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          document.getElementById("skillform").innerHTML=""
          document.getElementById("skillform").innerHTML+=`<label for="chooseskill">Choose a skill:</label>
          <select class="form-control" id="chooseskill">
          </select>`
          // Examine the text in the response
          response.json().then(function(data) {
            console.log(data);
            for (let i =0; i<data.length;i++) {

              let formoption = `<option>${data[i].skill_name}</option>`
            
            document.getElementById("chooseskill").innerHTML+=formoption;
            }
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
    })

document.querySelector("#bodydrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Body Tree"
    document.getElementById("picspace").innerHTML="<img src='bodytree.png' width=100%>"
    fetch(`http://localhost:8010/skills/listbytree/2`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          document.getElementById("skillform").innerHTML=""
          document.getElementById("skillform").innerHTML+=`<label for="chooseskill">Choose a skill:</label>
          <select class="form-control" id="chooseskill">
          </select>`
          // Examine the text in the response
          response.json().then(function(data) {
            console.log(data);
            for (let i =0; i<data.length;i++) {

              let formoption = `<option>${data[i].skill_name}</option>`
            
            document.getElementById("chooseskill").innerHTML+=formoption;
            }
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
})
document.querySelector("#resdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Resistance Tree"
    document.getElementById("picspace").innerHTML="<img src='resistancetree.png' width=100%>"
    fetch(`http://localhost:8010/skills/listbytree/3`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          document.getElementById("skillform").innerHTML=""
          document.getElementById("skillform").innerHTML+=`<label for="chooseskill">Choose a skill:</label>
          <select class="form-control" id="chooseskill">
          </select>`
          // Examine the text in the response
          response.json().then(function(data) {
            console.log(data);
            for (let i =0; i<data.length;i++) {

              let formoption = `<option>${data[i].skill_name}</option>`
            
            document.getElementById("chooseskill").innerHTML+=formoption;
            }
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
})
document.querySelector("#magicdrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Magic Tree"
    document.getElementById("picspace").innerHTML="<img src='magictree.png' width=100%>"
    fetch(`http://localhost:8010/skills/listbytree/4`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          document.getElementById("skillform").innerHTML=""
          document.getElementById("skillform").innerHTML+=`<label for="chooseskill">Choose a skill:</label>
          <select class="form-control" id="chooseskill">
          </select>`
          // Examine the text in the response
          response.json().then(function(data) {
            console.log(data);
            for (let i =0; i<data.length;i++) {

              let formoption = `<option>${data[i].skill_name}</option>`
            
            document.getElementById("chooseskill").innerHTML+=formoption;
            }
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
})
document.querySelector("#weapondrop").addEventListener("click", function(a) {
    a.preventDefault()
    document.getElementById("treehead").innerHTML="Weapon Tree"
    document.getElementById("picspace").innerHTML="<img src='weapontree.png' width=100%>"
    fetch(`http://localhost:8010/skills/listbytree/5`)
    .then(function(response) {
        if (response.status !== 200) {
            console.log('Looks like there was a problem. Status Code: ' +
              response.status);
            return;
          }
          document.getElementById("skillform").innerHTML=""
          document.getElementById("skillform").innerHTML+=`<label for="chooseskill">Choose a skill:</label>
          <select class="form-control" id="chooseskill">
          </select>`
          // Examine the text in the response
          response.json().then(function(data) {
            console.log(data);
            for (let i =0; i<data.length;i++) {

              let formoption = `<option>${data[i].skill_name}</option>`
            
            document.getElementById("chooseskill").innerHTML+=formoption;
            }
          });
        }
      )
      .catch(function(err) {
        console.log('Fetch Error :-S', err);
      });
})