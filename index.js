const express = require('express');
const app = express()
const cors = require('cors')
const router = require('./route/rrak-routes');

const PORT = 8000 || process.env.PORT

app.use(cors())
app.use(express.json())
app.use(express.urlencoded({extended : false}))

app.use('/', router)

app.listen(PORT, () => console.log('Server started on PORT ' + PORT));
