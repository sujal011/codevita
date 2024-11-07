class SequenceDetector:
    def __init__(self):
        self.state = 0
    def reset(self):
        self.state = 0
    def detect_sequence(self, sequence):
        detected = False
        for char in sequence:
            if self.state == 0:
                if char == 'a':
                    self.state = 1
            elif self.state == 1:
                if char == 'b':
                    self.state = 2
                elif char == 'a':
                    self.state = 1
                else:
                    self.reset()
            elif self.state == 2:
                if char == 'c':
                    detected = True
                    self.reset()
                else:
                    self.reset()
        return detected