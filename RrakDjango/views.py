# Create your views here.

from django.shortcuts import render
from django.views.decorators.csrf import csrf_exempt
from RrakDjango.models import *
from RrakDjango.serializer import PlantSerializer, PreserveSerializer
from django.http.response import JsonResponse
from rest_framework.parsers import JSONParser


@csrf_exempt
def getPlants(request):
    plants = Plant.objects.all()
    pSerializer = PlantSerializer(plants, many=True)
    return JsonResponse(pSerializer.data, safe=False)


@csrf_exempt
def getPreserves(request):
    preserves = Preserve.objects.all()
    pSerializer = PreserveSerializer(preserves, many=True)
    return JsonResponse(pSerializer.data, safe=False)


@csrf_exempt
def getPlantById(request, herb_id = 0):
    plant = Plant.objects.get(id=herb_id)
    pSerializer = PlantSerializer(plant)
    return JsonResponse(pSerializer.data, safe=False)


@csrf_exempt
def updatePlant(request):
    if request.method == 'POST':
        plantData = JSONParser().parse(request)
        plant = Plant.objects.get(id=plantData['id'])
        pSerializer = PlantSerializer(plant, data=plantData)
        if pSerializer.is_valid():
            pSerializer.save()
            return JsonResponse("Updated successfully", safe=False)
    return JsonResponse("Failed to update", safe=False)


@csrf_exempt
def createPlant(request):
    if request.method == 'POST':
        plant = JSONParser().parse(request)
        pSerializer = PlantSerializer(data=plant)
        if pSerializer.is_valid():
            pSerializer.save()
            return JsonResponse("Added successfully", safe=False)
    return JsonResponse("Failed to add", safe=False)


@csrf_exempt
def deletePlantById(request, herb_id = 0):
    plant = Plant.objects.get(id = herb_id)
    plant.delete()
    return JsonResponse("Deleted successfully", safe=False)