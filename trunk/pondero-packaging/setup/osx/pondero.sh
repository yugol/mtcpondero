#/bin/sh

JAVA=java

SOURCE="${BASH_SOURCE[0]}"
while [ -h "$SOURCE" ]; do # resolve $SOURCE until the file is no longer a symlink
  DIR="$( cd -P "$( dirname "$SOURCE" )" && pwd )"
  SOURCE="$(readlink "$SOURCE")"
  [[ $SOURCE != /* ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
done
PONDERO_HOME="$( cd -P "$( dirname "$SOURCE" )" && pwd )"

cd "$PONDERO_HOME"
$JAVA -jar ./bin/pondero-install.jar "$PONDERO_HOME"
$JAVA -cp ./bin/pondero.jar:./bin/pondero-libs.jar:./tests/* pondero.ui.Pondero "$PONDERO_HOME" &

