//Adjust your artifactory instance name/repository and your source code repository
def artifactory_name = "artifactory"
def artifactory_repo = "conan-local"
def repo_url = 'https://github.com/conan-community/conan-zlib.git'
def repo_branch = "release/1.2.11"
node {
    def server = Artifactory.server artifactory_name
    def client =    Artifactory.newConanClient()
    def serverName = client.remote.add server: server, repo: artifactory_repo
    stage("Get project"){
        git branch: repo_branch, url: repo_url
    }
        stage("Test recipe"){
        sh "export PATH=$PATH:/usr/local/bin && which cmake && conan create ."
    }
    stage("Upload packages"){
       String command = "upload \"*\" --all -r ${serverName} --confirm"
       def buildInfo = client.run command: command
        server.publishBuildInfo buildInfo
    }
}
