class FileHandler:
    def __init__(self, file_path):
        self.file_path = file_path

    def read_file(file_path):
        """Read and return the contents of a file."""
        try:
            with open(file_path, 'r') as file:
                return file.read()
        except Exception as e:
            print(f"Error occurred while reading file: {e}")
            return None


    def write_to_file(file_path, content):
        """Write content to a file."""
        try:
            with open(file_path, 'w') as file:
                file.write(content)
            print(f"Content written to {file_path}")
        except Exception as e:
            print(f"Error occurred while writing to file: {e}")


    def append_to_file(file_path, content):
        """Append content to a file."""
        try:
            with open(file_path, 'a') as file:
                file.write(content)
            print(f"Content appended to {file_path}")
        except Exception as e:
            print(f"Error occurred while appending to file: {e}")
