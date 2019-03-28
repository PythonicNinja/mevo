import os
import datetime as dt
from subprocess import Popen, PIPE

current_directory = os.path.dirname(os.path.abspath(__file__))

url = 'https://mevo-api.nextbike.net/maps/nextbike-live.xml'
output = f'{current_directory}/mevo_{dt.datetime.now().isoformat()}.xml'
args = ['wget', '-O', output, url]

output = Popen(args, stdout=PIPE)
