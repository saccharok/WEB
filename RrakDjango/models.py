# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Plant(models.Model):
    id = models.BigAutoField(primary_key=True)
    name = models.TextField(blank=True, null=True)
    description = models.TextField(blank=True, null=True)
    researchers = models.TextField(blank=True, null=True)
    status = models.TextField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'plant'


class Preserve(models.Model):
    id = models.BigAutoField(primary_key=True)
    name = models.TextField(blank=True, null=True)
    location = models.TextField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'preserve'


class PreservesPlant(models.Model):
    id = models.BigAutoField(primary_key=True)
    id_plant = models.OneToOneField(Plant, models.DO_NOTHING, db_column='id_plant')
    id_preserve = models.ForeignKey(Preserve, models.DO_NOTHING, db_column='id_preserve', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'preserves_plant'
        unique_together = (('id_plant', 'id'),)
