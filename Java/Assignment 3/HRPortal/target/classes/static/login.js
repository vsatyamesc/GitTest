function isValidEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.com$/;
    return re.test(email);
}

document.getElementById("loginButton").addEventListener("click",
    async () => {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    if (!isValidEmail(email)) { // !isValidUsername(email)
        alert("Please enter a valid email address.");
        return;
    }
        // Create JSON payload
    const credentials = {
        email: email,
        password: password
    };

    try {
        const response = await fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
            body: JSON.stringify(credentials)
        });
        console.log(response);
        if (response.ok) {
            const data = await response.json();
            // sessionStorage.setItem("username", JSON.stringify(data.username));
            // window.location.href = "dashboard.html";
            if (data.redirect) {
                window.location.href = data.redirect;
            } else {
                alert("Login successful, but no redirect URL provided.");
            }
        } else {
            alert("Invalid email or password. Please try again.");
        }
    } catch (error) {
        console.error("An error occurred:", error);
        alert("An error occurred while processing your request.");
    }
});

function isValidUsername(username) {
    const re = /^(30|AO|AA)[A-Za-z0-9]{8}$/
    return re.test(username);
}


