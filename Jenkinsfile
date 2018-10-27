node {
   def mvnHome
   def dockerUser = 'zashraj'
   def dockerPass = 'abc123'
   stage('Preparation') { // for display purposes
      git 'https://github.com/zashraj/restsample.git'
   }
   stage('Build') {
      // Run the maven build
      sh "mvn clean install"
      sh "docker build -t zashraj/myrestsample:latest ."
      sh "docker login -u${dockerUser} -p${dockerPass}"
      sh "docker push zashraj/myrestsample"
      
       
   }
   stage('Archive-war') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.war'
   }
   stage('Sonar') {
       sh "mvn sonar:sonar -Dsonar.host.url=http://mydevops.southindia.cloudapp.azure.com:9000/sonar"
   }
   stage('Run Docker image'){
       sh "docker-compose up -d"
   }
}
