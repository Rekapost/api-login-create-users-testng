const express = require('express');
const path = require('path');
const bodyParser = require('body-parser'); // To parse the body of requests
const app = express();
const port = 3004;

// Middleware to parse form data (URL-encoded)
app.use(bodyParser.urlencoded({ extended: true }));

// Middleware to parse JSON request bodies
app.use(express.json());

// Serve static files (like login.html)
app.use(express.static(path.join(__dirname, 'public')));

// Route to serve the login page
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'javascriptlogin.html'));
});

// Route to handle POST request
app.post('/login', (req, res) => {
    // Extract data from the incoming request body
    const uname = req.body.uname;
    const psw = req.body.psw;
    const remember = req.body.remember;
    
    // If "remember" is not present, default it to "off"
    const rememberStatus = remember === 'on' ? 'on' : 'off'; 

    // Log data to console for debugging
    console.log('Received data:', { uname, psw, remember });
    console.log('Form data received:',req.body); // This should show { uname, psw, remember }
    // Respond with the received data
  /*  
    if (!uname || !psw) {
        return res.status(400).json({
            status: 'error',
            message: 'Missing username or password'
        });
    }
    
    res.json({
        status: 'success',
        message: 'Login successful!',
        data: { uname, psw, remember } // Send the received data back
    });
    */
    if (uname === "reka12" && psw === "reka123") {
        // Send the desired response
        res.status(200).json({
            status: 'success',
            message: 'Login successful!',
            data: { uname, psw, remember: rememberStatus },
            token: 'rekatoken123' 
        });
    } else {
        res.status(401).json({ message: 'Invalid credentials!' });
    }
    /*   if (uname && psw) {
        // Your login POST logic (e.g., validating the data)
        res.json({
            status: 'success',
            message: 'Login successful!',
            data: { uname, remember: rememberStatus }
        });
    } else {
        res.status(400).json({ status: 'error', message: 'Missing uname or psw' });
    }
        */
});

// Start the server
app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});
