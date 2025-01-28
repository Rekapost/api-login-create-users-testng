fetch('https://jsonplaceholder.typicode.com/posts', {
    method: 'POST',
    body: formData
})
.then(response => response.json())
.then(data => {
    console.log('Success:', data);
    alert('Login successful!');
})
.catch(error => {
    console.error('Error:', error);
    alert('Error during login');
});
