#define echoPin 2
#define trigPin 3
#define led 4
int speakerPin = 9;
int numTones = 4;
int tones[] = {440, 294, 440, 294};

void setup() {
  Serial.begin (9600);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(led, OUTPUT);
  
}

void loop() {
  long duration, distance;
  digitalWrite(trigPin, LOW);  // Added this line
  delayMicroseconds(2); // Added this line
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10); // Added this line
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  distance = (duration/2) / 50;
  if (distance > 50) {  // This is where the LED On/Off happens
  digitalWrite(led,LOW);
}
  else {
   Serial.print(distance);
    digitalWrite(led,HIGH);
    for (int i = 0; i < numTones; i++)
  {
    tone(speakerPin, tones[i]);
    delay(100);
  }
  noTone(speakerPin);
  
  }
  
  
}
