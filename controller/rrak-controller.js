const db = require('../db')

class RrakController {

    async getPlants(req, res) {
        const plants = await db.query('select * from plant')
        console.log(plants.rows)
        res.json({data: plants.rows})
    }

    async getPreserves(req, res) {
        const preserves = await db.query('select * from preserve')
        res.json({data: preserves.rows})
    }

    async getPlantById(req, res) {
        const id = req.params.id
        const plant = await db.query('select * from plant where id = $1', [id])
        const preserves = await db.query('select get_preserves($1);', [plant.rows[0].id])
        res.json({plant: plant.rows[0], preserves: preserves.rows[0]})
    }

    async updatePlant(req, res) {
        const { id, name, description, researchers, status, preserves } = req.body
        const updated = await db.query('update plant set name = $1, description = $2, researchers = $3, status = $4 where id = $5 RETURNING *', [name, description, researchers, status, id])
        res.json(updated)
    }

    async deletePlant(req, res) {
        const id = req.params.id
        await db.query('delete from plant where id = $1', [id])
        res.json('deleted')
    }

    async createPlant(req, res) {
        const { id, name, description, researchers, status, preserves } = req.body
        const newPlant = await db.query('insert into plant (name, description, researchers, status) values ($1, $2, $3, $4) RETURNING *', [name, description, researchers, status])
        res.json(newPlant.rows[0])
    }
}

module.exports = new RrakController()
