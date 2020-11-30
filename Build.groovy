//Adjust your artifactory instance name/repository and your source code repository
def artifactory_name = "artifactory"
def artifactory_repo = "conan-local"
def repo_url = 'https://github.com/memsharded/example-boost-poco.git'
def repo_branch = 'master'

node {
    def server = Artifactory.server artifactory_name
    def client = Artifactory.newConanClient()

    stage("Get project"){
        git branch: repo_branch, url: repo_url
    }

    stage("Get dependencies and publish build info"){
        sh "mkdir -p build"
        dir ('build') {
            sh "conan install .. --build=missing"

        //   def b = client.run(command: "install .. --build=missing")
        //   server.publishBuildInfo b
        }
    }

    stage("Build/Test project"){
        dir ('build') {
            sh '''/usr/local/bin/cmake .. -G "Unix Makefiles" -DCMAKE_BUILD_TYPE=Release'''
            sh "/usr/local/bin/cmake --build ."
        }
    }
}
