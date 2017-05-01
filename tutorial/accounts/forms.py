from django import forms
from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm, UserChangeForm

from accounts.models import UserProfile


class RegistrationForm(UserCreationForm):
    #email = forms.EmailField(required=True)

    class Meta:
        model = Usuario
        fields = ['cpf', 'telefone', 'username',
        'password1', 'password2']

    def save(self, commit=True):
        user = super(RegistrationForm, self).save(commit=False)
        user.first_name = self.cleaned_data['cpf']
        user.last_name = self.cleaned_data['telefone']
        #user.email = self.cleaned_data['email']

        if commit:
            user.save()

        return user
