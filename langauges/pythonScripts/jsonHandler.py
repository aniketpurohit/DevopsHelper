import json 

class JsonHandler:
    def __init__(self, file_path):
        self.file_path = file_path

    def read_json(self):
        try:
            with open(self.file_path, 'r') as file:
                data = json.load(file)
            return data
        except FileNotFoundError:
            print(f"File not found: {self.file_path}")
            return None
        except json.JSONDecodeError:
            print(f"Error decoding JSON file: {self.file_path}")
            return None
        
    def dict_to_json(data_dict):
        """Convert a Python dictionary to a JSON string."""
        try:
            return json.dumps(data_dict, indent=4)
        except Exception as e:
            print(f"Error converting dict to JSON: {e}")
            return None
        


    def write_json(self, data):
        try:
            with open(self.file_path, 'w') as file:
                json.dump(data, file, indent=4)
        except json.JSONDecodeError:
            print(f"Error encoding JSON data: {data}")
        except Exception as e:
            print(f"An error occurred while writing to the file: {e}")