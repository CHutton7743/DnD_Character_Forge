function initializePage() {
    // Hide all tabs except the first one
    let tabcontent = document.getElementsByClassName("tab");
    for (let i = 1; i < tabcontent.length; i++) {
        tabcontent[i].classList.remove("active");
    }

    // Deactivate all tab buttons except the first one
    let tablinks = document.getElementsByClassName("tab-button");
    for (let i = 1; i < tablinks.length; i++) {
        tablinks[i].classList.remove("active");
    }

    // Load characters when tab is activated
    let charListTab = document.getElementById("char-list");
    charListTab.addEventListener("click", function() {
        loadCharacters();
    });

    // Load character creation form when tab is activated
    let charCreateTab = document.getElementById("char-create");
    charCreateTab.addEventListener("click", function() {
        loadCharacterCreationForm();
    });
}

// Function to show/hide tabs
function openTab(evt, tabName) {
    let i, tabcontent, tablinks;

    // Get all elements with class="tab" and remove the class "active"
    tabcontent = document.getElementsByClassName("tab");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].classList.remove("active");
    }

    // Get all elements with class="tab-button" and remove the class "active"
    tablinks = document.getElementsByClassName("tab-button");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].classList.remove("active");
    }

    // Show the current tab, and add an "active" class to the button that opened the tab
    document.getElementById(tabName).classList.add("active");
    evt.currentTarget.classList.add("active");
}


// Load characters when tab is activated
let charListTab = document.getElementById("char-list");
charListTab.addEventListener("click", function() {
    loadCharacters();
});

// Function to submit the first form and show the second form
function characterCreationProcess() {
    // create a JSON object with the data from the first form, hide the first form, and show the second form
    let character = {
        name: document.getElementById("name").value,
        race: document.getElementById("race").value,
        class: document.getElementById("class").value,
        level: document.getElementById("level").value,
        experience: document.getElementById("experience").value,
        background: document.getElementById("background").value,
    }

}

// function to populate the character list table with the characters from the database
function loadCharacters() {
    let userEmail = document.getElementById("email").value;
    // make a request to the server to get the characters for the user with the given email address
    let request = new XMLHttpRequest();
    request.open("GET", "http://localhost:8080/api/v1/characters", true);
    request.setRequestHeader("Content-Type", "application/json");
    request.send();

    let table = document.getElementById("char-list").getElementsByTagName("tbody")[0];


}



// Function to show the second form and hide the first form
function nextForm(formNum) {
    if (formNum === 2) {
        document.getElementById("form1").style.display = "none";
        document.getElementById("form2").style.display = "block";
    }
}

function showChangeEmailForm() {
    document.getElementById("change-email-form").style.display = "block";
}

function showChangePasswordForm() {
    document.getElementById("change-password-form").style.display = "block";
}

function showChangeUsernameForm() {
    document.getElementById("change-username-form").style.display = "block";
}

function changeEmail() {
    const email = document.getElementById("new-email").value;
    fetch('/api/v1/user/updateEmail', {
        method: 'POST',
        body: JSON.stringify({newEmail: email}),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Email change failed');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            alert('Email changed successfully');
        })
        .catch(error => {
            console.error(error);
            alert('Email change failed');
        });
}

function changePassword() {
    const currentPassword = document.getElementById("current-password").value;
    const newPassword = document.getElementById("new-password").value;
    fetch('/api/v1/user/updatePassword', {
        method: 'POST',
        body: JSON.stringify({currentPassword: currentPassword, newPassword: newPassword}),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Password change failed');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            alert('Password changed successfully');
        })
        .catch(error => {
            console.error(error);
            alert('Password change failed');
        });
}

function changeUsername() {
    const username = document.getElementById("new-username").value;
    fetch('/api/v1/user/updateUsername', {
        method: 'POST',
        body: JSON.stringify({newUserName: username}),
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Username change failed');
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            alert('Username changed successfully');
        })
        .catch(error => {
            console.error(error);
            alert('Username change failed');
        });
}


function populateCharacterTable() {
    const userEmail = document.getElementById("email").value;
    const url = "http://localhost:8080/api/v1/characters";
    const request = new XMLHttpRequest();
    request.open("GET", url);
    request.setRequestHeader("Content-Type", "application/json");
    request.onload = function() {
        // handle response
    };
    const requestBody = JSON.stringify({ email: userEmail });
    request.send(requestBody);
    // populate character table

}


// Function to randomly generate ability scores and fill in the input fields
function rollStats() {
    // roll 4d6, drop the lowest, and check if total is >= 70. If not, roll again
    let total = 0;
    while (total < 70) {
        total = 0;
        for (let i = 0; i < 6; i++) {
            let rolls = [];
            for (let j = 0; j < 4; j++) {
                rolls.push(Math.floor(Math.random() * 6) + 1);
            }
            rolls.sort();
            rolls.shift();
            total += rolls.reduce((a, b) => a + b, 0);
        }
    }
    document.getElementById("strength").value = Math.floor(Math.random() * 16) + 3;
    document.getElementById("dexterity").value = Math.floor(Math.random() * 16) + 3;
    document.getElementById("constitution").value = Math.floor(Math.random() * 16) + 3;
    document.getElementById("intelligence").value = Math.floor(Math.random() * 16) + 3;
    document.getElementById("wisdom").value = Math.floor(Math.random() * 16) + 3;
    document.getElementById("charisma").value = Math.floor(Math.random() * 16) + 3;
}

// Function to fill in the input fields with the standard ability score arrangement
function standardArrangement() {
    document.getElementById("strength").value = 15;
    document.getElementById("dexterity").value = 14;
    document.getElementById("constitution").value = 13;
    document.getElementById("intelligence").value = 12;
    document.getElementById("wisdom").value = 10;
    document.getElementById("charisma").value = 8;
}

function submitForm() {
}