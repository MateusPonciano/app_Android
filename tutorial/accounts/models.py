from django.db import models
from django.contrib.auth.models import User
from django import forms
from django.contrib.auth.forms import UserCreationForm

class Usuario(User):
    cpf = models.CharField(u'cpf', max_length=11)
    telefone = models.CharField(u'telefone', max_length=15)

    def __unicode__(self):
        return self.cpf


class Cadastrar(forms.ModelForm):

    class Meta:
        model = User

        fields = ['username',
        'password',
        'email',]
