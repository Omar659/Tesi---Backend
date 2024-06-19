import qi
import sys 
import motion
import math
import time
# import almath

class Authenticator:

    def __init__(self, username, password):
        self.username = username
        self.password = password

    # This method is expected by libqi and must return a dictionary containing
    # login information with the keys 'user' and 'token'.
    def initialAuthData(self):
        return {'user': self.username, 'token': self.password}


class AuthenticatorFactory:

    def __init__(self, username, password):
        self.username = username
        self.password = password

    # This method is expected by libqi and must return an object with at least
    # the `initialAuthData` method.
    def newAuthenticator(self):
        return Authenticator(self.username, self.password)



sys.path.append('./utils')
from api_call import *
import keyboard
import time

# api = API_call("http://192.168.210.120:8080", "flagPepperHMD/getFlag")
# api = API_call("http://192.168.210.120:8080", "flagPepperHMD/switchFlag")





api = API_call("http://192.168.210.120:8080", "flagPepperHMD")
doAction = True

while True:
    response = api.call('get', "getFlag", 25, ['flagName', "flag"])

    if response["flagName"] == "flag" and response["value"] and doAction:
        print("NELL'IF SE NON FACCIO AZIONE\t\t\tflagName: " + response["flagName"] + "; value: " + str(response["value"]) + "; doAction: " + str(doAction))
        if keyboard.is_pressed('d'):
            print("FACCIO AZIONE\t\t\tflagName: " + response["flagName"] + "; value: " + str(response["value"]) + "; doAction: " + str(doAction))
            doAction = False
            _ = api.call('put', "switchFlag", 25, ['flagName', "flag"])
                    
    if response["flagName"] == "flag" and response["value"]:
        print("CHECK SULLA CHIAMATA API\t\t\tflagName: " + response["flagName"] + "; value: " + str(response["value"]) + "; doAction: " + str(doAction))


    time.sleep(0.1)



# # Connect to the robot fails at app.start() => RuntimeError: disconnected
# app = qi.Application(sys.argv, url="tcps://192.168.137.41:9503")
# logins = ("nao", "vision@2024")
# factory = AuthenticatorFactory(*logins)
# app.session.setClientAuthenticatorFactory(factory)
# app.start()
# print("started")

# tts = app.session.service("ALTextToSpeech")
# tts.say('''Hi Hitler''')

# motion_service = app.session.service("ALMotion")
# motion_service.wakeUp()
# # effector   = "RShoulderPitch"
# # frame      = motion.FRAME_TORSO
# # useSensorValues = False
# # currentTf = motion_service.getTransform(effector, frame, useSensorValues)
# # print(currentTf)

# isAbsolute = True
        
# # First motion
# jointNames = ["RShoulderPitch", "RWristYaw", "RHand"]
# jointValues = [math.radians(-160), math.radians(-70), math.radians(200)]
# times = [1, 1, 1]



# motion_service.angleInterpolation(jointNames, jointValues, times, isAbsolute)
# time.sleep(2)

# app.stop()
# sys.exit(0)