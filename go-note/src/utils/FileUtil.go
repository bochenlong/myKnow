package file

import (
	"os"
)

func New(path string) (*os.File, error) {
	file, err := os.Create(path)
	defer file.Close()
	if err != nil {
		return err
	}
	return file
}
