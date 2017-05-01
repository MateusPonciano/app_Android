from django.shortcuts import render, redirect
from django.http import HttpResponse, JsonResponse, HttpRequest, QueryDict
from accounts.models import Usuario, Cadastrar
from django.contrib.auth import authenticate, login, logout
from django.views.decorators.csrf import csrf_exempt
import json
import codecs

#def index(request):
#    return HttpResponse('OK')

@csrf_exempt
def cadastro(request):
    data = dict()
    data['success'] = False
    #reader = codecs.getreader("utf-8")
    #json_data=json.load(request.body.decode('utf-8'))
    received_data = request.body.decode('utf-8')
    json_data = json.loads(received_data)
    form_data_immutable = QueryDict()
    form_data = form_data_immutable.copy()
    form_data.__setitem__('username', json_data['username'])
    form_data.__setitem__('password', json_data['password'])
    #form_data.__setitem__('password2', json_data['password'])
    #form_data.__setitem__('cpf', json_data['cpf'])
    #form_data.__setitem__('telefone', json_data['telefone'])
    form_data.__setitem__('email', json_data['email'])

    if request.method == 'GET':
        '''

        info = dict()
        info = {

        'username': request.GET['usuario'],
        'password': request.GET['senha'],
        'cpf' : request.GET['cpf'],
        'telefone' : request.GET['telefone']

        }
        form = Cadastrar(request.GET)

        if form.is_valid():
            #form.save()
            data['success'] = True
        '''
        pass


    elif request.method == 'POST':
        form = Cadastrar(form_data)
        #data['success'] = True

        if form.is_valid():
            data['success']=True
            form.save()
        else:
            form = Cadastrar()

    return JsonResponse(data)
    #return JsonResponse(form_data)


@csrf_exempt
def do_login(request):
    data = dict()
    data['success'] = False
    if request.method =='GET':
        user = authenticate(username=request.GET['usuario'],
        password=request.GET['senha'])
        if user is not None:
            login(request, user)
            data = {

                'success' : True,
                'username': user.username,
                'password': user.password,

            }
    elif request.method =='POST':
        received_data = request.body.decode('utf-8')
        json_data = json.loads(received_data)

        user = authenticate(username=json_data['username'],
        password=json_data['password'])
        if user is not None:
            login(request, user)
            data = {

                'success' : True,
                'username': user.username,
                'password': user.password,

            }
    return JsonResponse(data)

@csrf_exempt
def do_logout(request):
    logout(request)
    return redirect('/accounts/login')
