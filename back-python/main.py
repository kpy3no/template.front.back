from controller import run, HttpHandler

if __name__ == '__main__':
    handler = HttpHandler
    run(handler_class=HttpHandler)