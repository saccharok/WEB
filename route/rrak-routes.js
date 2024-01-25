const Router = require('express')
const router = new Router()
const controller = require("../controller/rrak-controller")

router.get('/plants', controller.getPlants)
router.get('/preserves', controller.getPreserves)
router.get('/herb/:id', controller.getPlantById)
router.post('/edit-herb', controller.updatePlant)
router.get('/herb/delete/:id', controller.deletePlant)
router.post('/add-herb', controller.createPlant)

//router.post('/herb/:id', controller.updatePlant)

module.exports = router
