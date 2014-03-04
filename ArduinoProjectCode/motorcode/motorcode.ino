int motorPin = 9;          

int i=0;

void setup()                    
{
  pinMode(motorPin, OUTPUT);
}
void loop()                     
{   
  analogWrite(motorPin, 255);
}
