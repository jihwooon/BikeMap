import express from 'express'

let app = express();

app.get('/', async (req, res) => {
    res.send('Hello Node!');
});
export default app;
