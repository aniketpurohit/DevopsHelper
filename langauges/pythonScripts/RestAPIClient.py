import requests
import json
from requests.auth import HTTPBasicAuth

class RestApiClient:
    def __init__(self, base_url, auth_type=None, username=None, password=None, token=None,
                 verify_ssl=True, headers=None, timeout=30):
        """
        Initialize the API client with the base URL for the REST API.
        Optionally, you can provide authentication details (basic auth or token).
        
        :param base_url: The base URL for the API (e.g., 'https://api.example.com')
        :param auth_type: Type of authentication ('basic' or 'bearer'). Default is None (no authentication).
        :param username: Username for basic authentication (optional).
        :param password: Password for basic authentication (optional).
        :param token: Bearer token for token-based authentication (optional).
        :param verify_ssl: Whether to verify SSL certificates (default: True).
        :param headers: Optional dictionary of default headers to send with each request (default: None).
        :param timeout: Timeout in seconds for each request (default: 30).
        """
        self.base_url = base_url
        self.auth_type = auth_type
        self.username = username
        self.password = password
        self.token = token
        self.verify_ssl = verify_ssl
        self.headers = headers if headers else {}
        self.timeout = timeout

    def get_auth(self):
        """
        Return the appropriate authentication method based on the auth_type.
        :return: Authentication object (None, HTTPBasicAuth, or headers with Bearer token)
        """
        if self.auth_type == 'basic':
            return HTTPBasicAuth(self.username, self.password)
        elif self.auth_type == 'bearer' and self.token:
            return {'Authorization': f'Bearer {self.token}'}
        else:
            return None  # No authentication

    def get(self, endpoint, params=None):
        """
        Send a GET request to the API.
        :param endpoint: The specific API endpoint (e.g., '/users')
        :param params: Optional dictionary of query parameters to send with the request
        :return: Response from the API
        """
        url = f"{self.base_url}{endpoint}"
        auth = self.get_auth()
        headers = {**self.headers, **(auth if isinstance(auth, dict) else {})}  # Merge default headers with auth headers
        try:
            response = requests.get(url, params=params, headers=headers, 
                                    auth=auth if isinstance(auth, HTTPBasicAuth) else None, 
                                    verify=self.verify_ssl, timeout=self.timeout)
            response.raise_for_status()  # Raise an exception for HTTP errors (4xx, 5xx)
            return response.json()  # Return the JSON response
        except requests.exceptions.RequestException as e:
            print(f"Error occurred while making GET request: {e}")
            return None

    def post(self, endpoint, data=None):
        """
        Send a POST request to the API.
        :param endpoint: The specific API endpoint (e.g., '/users')
        :param data: Optional dictionary or JSON data to send in the body of the POST request
        :return: Response from the API
        """
        url = f"{self.base_url}{endpoint}"
        auth = self.get_auth()
        headers = {**self.headers, **(auth if isinstance(auth, dict) else {})}  # Merge default headers with auth headers
        try:
            response = requests.post(url, json=data, headers=headers, 
                                     auth=auth if isinstance(auth, HTTPBasicAuth) else None, 
                                     verify=self.verify_ssl, timeout=self.timeout)
            response.raise_for_status()  # Raise an exception for HTTP errors
            return response.json()  # Return the JSON response
        except requests.exceptions.RequestException as e:
            print(f"Error occurred while making POST request: {e}")
            return None

    def put(self, endpoint, data=None):
        """
        Send a PUT request to the API.
        :param endpoint: The specific API endpoint (e.g., '/users/1')
        :param data: Optional dictionary or JSON data to send in the body of the PUT request
        :return: Response from the API
        """
        url = f"{self.base_url}{endpoint}"
        auth = self.get_auth()
        headers = {**self.headers, **(auth if isinstance(auth, dict) else {})}  # Merge default headers with auth headers
        try:
            response = requests.put(url, json=data, headers=headers, 
                                    auth=auth if isinstance(auth, HTTPBasicAuth) else None, 
                                    verify=self.verify_ssl, timeout=self.timeout)
            response.raise_for_status()  # Raise an exception for HTTP errors
            return response.json()  # Return the JSON response
        except requests.exceptions.RequestException as e:
            print(f"Error occurred while making PUT request: {e}")
            return None

    def delete(self, endpoint):
        """
        Send a DELETE request to the API.
        :param endpoint: The specific API endpoint (e.g., '/users/1')
        :return: Response from the API
        """
        url = f"{self.base_url}{endpoint}"
        auth = self.get_auth()
        headers = {**self.headers, **(auth if isinstance(auth, dict) else {})}  # Merge default headers with auth headers
        try:
            response = requests.delete(url, headers=headers, 
                                       auth=auth if isinstance(auth, HTTPBasicAuth) else None, 
                                       verify=self.verify_ssl, timeout=self.timeout)
            response.raise_for_status()  # Raise an exception for HTTP errors
            return response.json()  # Return the JSON response
        except requests.exceptions.RequestException as e:
            print(f"Error occurred while making DELETE request: {e}")
            return None


    
