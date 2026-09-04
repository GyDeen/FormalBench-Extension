<#
.SYNOPSIS
Activates the project-local MSYS2 UCRT64 C toolchain in the current PowerShell session.

.USAGE
    . .\tools\use-msys2-c.ps1
#>

$msysRoot = 'C:\msys64'
$gccDirectory = Join-Path $msysRoot 'ucrt64\bin'
$msysDirectory = Join-Path $msysRoot 'usr\bin'

if (-not (Test-Path -LiteralPath (Join-Path $gccDirectory 'gcc.exe'))) {
    throw "MSYS2 UCRT64 GCC was not found at $gccDirectory"
}

foreach ($directory in @($gccDirectory, $msysDirectory)) {
    if (-not (@($env:Path -split ';') -contains $directory)) {
        $env:Path = "$directory;$env:Path"
    }
}

Write-Output "Activated MSYS2 UCRT64 C toolchain: $gccDirectory"
Write-Output "gcc=$((Get-Command gcc).Source)"
Write-Output "gdb=$((Get-Command gdb).Source)"
Write-Output "mingw32-make=$((Get-Command mingw32-make).Source)"
