pipeline {
    agent any

    triggers {
        // Déclenche le pipeline à chaque push Git (via webhook GitHub/GitLab)
        pollSCM('* * * * *')  // vérifie toutes les minutes (optionnel si tu as déjà un webhook)
    }

    stages {
        stage('Récupération du code source') {
            steps {
                // Clone le dépôt Git associé au job Jenkins
                checkout scm
            }
        }

        stage('Nettoyage du dossier target') {
            steps {
                sh 'rm -rf target'
            }
        }

        stage('Compilation') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Création du livrable (package)') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
    }

    post {
        success {
            echo "✅ Build réussi, livrable créé dans target/"
        }
        failure {
            echo "❌ Erreur dans le pipeline"
        }
    }
}