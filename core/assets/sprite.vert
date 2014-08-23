
attribute vec3 a_position;
attribute vec4 a_color;
attribute vec2 a_texCoords;

uniform mat4 u_projection;
uniform vec2 u_scale;

uniform vec3 i_position;

varying vec4 v_color;
varying vec2 v_texCoords;

void main()
{
	v_color = a_color;
	v_texCoords = a_texCoords;

	gl_Position = u_projection * vec4(i_position.xy + (a_position.xy * u_scale), i_position.z, 1.0);
}