#ifdef GL_ES
precision mediump float;
#endif

uniform sampler2D u_texture0;

uniform vec4 i_texCoords;

varying vec4 v_color;
varying vec2 v_texCoords;

void main()
{
	gl_FragColor =  texture2D(u_texture0, i_texCoords.xy + v_texCoords*i_texCoords.zw);
}