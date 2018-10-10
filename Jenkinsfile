node {
   def mvnHome
   stage('Preparation') { // for display purposes
      git 'https://github.com/zashraj/restsample.git'
   }
   stage('Build') {
      // Run the maven build
        sh "mvn clean install"
       
   }
   stage('Archive-war') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.war'
      sh "cp target/restsample.war /var/tmp/warfiles/target/restsample.war "
   }
   stage('Sonar') {
       sh "mvn sonar:sonar -Dsonar.host.url=http://18.224.172.45:9000/sonar"
   }
   stage('Run Docker image'){
       sh "docker-compose up -d"
   }
}
