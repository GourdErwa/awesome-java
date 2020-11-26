
pwd=`pwd`

echo "home: " $pwd

files=`ls $dir`

for file in $files
do
	echo "project: "$file

	cd $file
	`git pull`
	cd ../
done
