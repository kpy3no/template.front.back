import json
from http import HTTPStatus
from http.server import BaseHTTPRequestHandler
from http.server import HTTPServer
from typing import List

from model.dto import City
from service.repository import get_all_city


def run(server_class=HTTPServer, handler_class=BaseHTTPRequestHandler):
  server_address = ('', 8889)
  httpd = server_class(server_address, handler_class)
  try:
      httpd.serve_forever()
  except KeyboardInterrupt:
      httpd.server_close()

class HttpHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        if self.path == '/cities':
            self.get_cities()

    def get_cities(self):
        self._set_headers()
        cities: List[City] = get_all_city()
        response = json.dumps(cities, default=vars)
        response = bytes(response, 'utf-8')
        self.wfile.write(response)

    # Borrowing from https://gist.github.com/nitaku/10d0662536f37a087e1b
    def _set_headers(self):
        self.send_response(HTTPStatus.OK)
        self.send_header('Content-type', 'application/json')
        # Allow requests from any origin, so CORS policies don't
        # prevent local development.
        self.send_header('Access-Control-Allow-Origin', '*')
        self.end_headers()