import requests

class API_call():
    def __init__(self, base_url, resource, headers={"Content-Type": "application/json"}):
        self.base_url = base_url + "/" + resource
        self.headers = headers

    def call(self, type, request_name, timeout, *params):
        url = self.base_url + "/" + request_name + "?"
        for param in params:
            url += param[0] + "=" + param[1] + "&"
        url = url[:-1]
        print("Request url: " + url)
        if type.upper() == "GET":
            response = requests.get(url, headers=self.headers, timeout=timeout)
        if type.upper() == "PUT":
            response = requests.put(url, headers=self.headers, timeout=timeout)
        if type.upper() == "POST":
            response = requests.post(url, headers=self.headers, timeout=timeout)
        if type.upper() == "DELETE":
            response = requests.delete(url, headers=self.headers, timeout=timeout)
        print("Status code: " + str(response.status_code))
        json_response = None
        if response.status_code == 200:
            if response is not None:
                json_response = response.json()
            return json_response
        return {"message": "Request failed", "error": True}