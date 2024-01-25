from rest_framework import serializers
from RrakDjango.models import *

class PlantSerializer(serializers.ModelSerializer):
    class Meta:
        model = Plant
        fields = ('id', 'name', 'description', 'researchers', 'status')

class PreserveSerializer(serializers.ModelSerializer):
    class Meta:
        model = Preserve
        fields = ('id', 'name', 'location')

class PreservesNPlantSerializer(serializers.ModelSerializer):
    class Meta:
        model = PreservesPlant
        fields = ('id', 'id_plant', 'id_preserve')