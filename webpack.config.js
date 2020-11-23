const webpack = require('webpack');
const path = require('path');

const config = {
    target: 'web',
    module: {
        rules: [
            {
                test: /\.js$/,
                enforce: 'pre',
                use: ['source-map-loader'],
            },
            {
                test: /\.css$/,
                use: ['style-loader',
                      'css-loader',
                     ],
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: ['file-loader'],
            },
            {
                test: /\.(woff|woff2|eot|ttf|otf)$/,
                use: ['file-loader'],
            },
        ],
    },
    node: {
        // For stopify
        'fs': 'empty',
        'child_process': 'empty',
        'net': 'empty',
        'module': 'empty',
    },
};

module.exports = config;
