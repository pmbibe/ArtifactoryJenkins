# ArtifactoryJenkins
As admin in sonarqube, go to https://my-sonarqube.tld/admin/webhooks configure the url to be https://my-jenkins-domain.tld/sonarqube-webhook/  
Alternatively, you can set a webhook per invocation/scan of a project. Either on the cli -Dsonar.webhooks.project=https://my-jenkins-domain.tld/sonarqube-webhook/ or in sonar-project.properties sonar.webhooks.project=https://my-jenkins-domain.tld/sonarqube-webhook/
