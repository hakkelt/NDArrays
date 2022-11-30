# Steps to export tutorial

1. Change current directory to `tutorial-src`: `cd .\tutorial-src`
2. Link template to nbconvert's template directory: `mklink /J .\templates\allTheKernels\ C:\Users\thakkel\.pyenv\pyenv-win\versions\3.6.8\share\jupyter\nbconvert\templates\allTheKernels`
3. Run nbconvert: `jupyter nbconvert --to html --template allTheKernels ./NDArrays.ipynb --output ..\docs\tutorial\index.html`