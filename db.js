let Pool = require('pg').Pool

const pool = new Pool({
        user: "postgres",
        password: "postgres",
        host: "localhost",
        post: 5432,
        database: "rrak"
    }
)

module.exports = pool
